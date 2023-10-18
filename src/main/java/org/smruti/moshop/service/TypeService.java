package org.smruti.moshop.service;

import java.util.ArrayList;
import java.util.List;

import org.smruti.moshop.model.Type;
import org.smruti.moshop.repository.TypeRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TypeService {
    private final TypeRepository typeRepository;

    public void createOrUpdate(Type type) {
        typeRepository.save(type);
    }

    public List<Type> getTypes() {
        return typeRepository.findAll();
    }

    public List<Type> getTypesById(List<String> types) {
        if (types == null || types.isEmpty())
            return new ArrayList<>();
        return typeRepository.findAllById(types);
    }
}
