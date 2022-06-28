package com.acme.digol.digol.mapping;

import com.acme.digol.digol.domain.model.entity.SportField;
import com.acme.digol.digol.domain.model.entity.User;
import com.acme.digol.digol.resource.*;
import com.acme.digol.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class UserMapper  implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;
    public UserResource toResource(User model) {
        return mapper.map(model, UserResource.class);
    }

    public Page<UserResource> modelListPage(List<User> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, UserResource.class), pageable, modelList.size());
    }

    public User toModel(CreateUserResource resource) {
        return mapper.map(resource, User.class);
    }

    public User toModel(UpdateUserResource resource) {
        return mapper.map(resource, User.class);
    }
}
