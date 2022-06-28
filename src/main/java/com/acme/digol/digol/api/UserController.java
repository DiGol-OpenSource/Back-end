package com.acme.digol.digol.api;

import com.acme.digol.digol.domain.service.UserService;
import com.acme.digol.digol.mapping.UserMapper;
import com.acme.digol.digol.resource.*;
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

@Tag(name = "Users")
@RestController
@RequestMapping("api/v1/users")
public class UserController {
    private final UserService userService;

    private final UserMapper mapper;

    public UserController(UserService userService, UserMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }


    @Operation(summary = "Get User", description = "Get All Users.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SportFieldResource.class))})
    })
    @GetMapping
    public Page<UserResource> getAllUsers(Pageable pageable) {
        return mapper.modelListPage(userService.getAll(), pageable);
    }
    @Operation(summary = "Get User  with id ", description = "Get User with id.")
    @GetMapping("{userId}")
    public UserResource getUserById(@PathVariable Long userId) {
        return mapper.toResource(userService.getById(userId));
    }
    @Operation(summary = "Post User ", description = "Post  User")
    @PostMapping
    public UserResource createUser(@RequestBody CreateUserResource resource) {
        return mapper.toResource(userService.create(mapper.toModel(resource)));
    }
    @Operation(summary = "Edit User ", description = "Edit User.")
    @PutMapping("{userId}")
    public UserResource updateUser(@PathVariable Long userId, @RequestBody UpdateUserResource resource) {
        return mapper.toResource(userService.update(userId, mapper.toModel(resource)));
    }
    @Operation(summary = "Delete User", description = "Delete User.")
    @DeleteMapping("{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        return userService.delete(userId);
    }
}
