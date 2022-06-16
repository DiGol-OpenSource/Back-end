package com.acme.digol.digol.api;
import com.acme.digol.digol.domain.service.SportFieldService;
import com.acme.digol.digol.mapping.SportFieldMapper;
import com.acme.digol.digol.resource.CreateSportFieldResource;
import com.acme.digol.digol.resource.SportFieldResource;
import com.acme.digol.digol.resource.UpdateSportFieldResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "SportsFields")
@RestController
@RequestMapping("api/v1/sportsFields")
public class SportFieldController {
    private final SportFieldService sportFieldService;

    private final SportFieldMapper mapper;

    public SportFieldController(SportFieldService sportFieldService, SportFieldMapper mapper) {
        this.sportFieldService = sportFieldService;
        this.mapper = mapper;
    }

    @Operation(summary = "Get Sport Field", description = "Get All Sports Fields.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SportField found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SportFieldResource.class))})
    })
    @GetMapping
    public Page<SportFieldResource> getAllSportsFields(Pageable pageable) {
        return mapper.modelListPage(sportFieldService.getAll(), pageable);
    }
    @Operation(summary = "Get Sport Field with id ", description = "Get  Sport Field with id.")
    @GetMapping("{sportFieldId}")
    public SportFieldResource getSportFieldById(@PathVariable Long sportFieldId) {
        return mapper.toResource(sportFieldService.getById(sportFieldId));
    }
    @Operation(summary = "Post Sport Field", description = "Post  Sport Field")
    @PostMapping
    public SportFieldResource createStudent(@RequestBody CreateSportFieldResource resource) {
        return mapper.toResource(sportFieldService.create(mapper.toModel(resource)));
    }
    @Operation(summary = "Edit Sport Field", description = "Edit  Sport Field.")
    @PutMapping("{sportFieldId}")
    public SportFieldResource updateStudent(@PathVariable Long sportFieldId, @RequestBody UpdateSportFieldResource resource) {
        return mapper.toResource(sportFieldService.update(sportFieldId, mapper.toModel(resource)));
    }
    @Operation(summary = "Delete Sport Field", description = "Delete  Sport Field.")
    @DeleteMapping("{sportFieldId}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long sportFieldId) {
        return sportFieldService.delete(sportFieldId);
    }
}
