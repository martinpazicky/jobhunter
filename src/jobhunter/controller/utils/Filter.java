package jobhunter.controller.utils;

import jobhunter.model.Specialist;
import java.util.List;
import java.util.stream.Collectors;

public class Filter {

    public static List<Specialist> filterByName(List<Specialist> all, String name){
        return all.stream()
                .filter(specialist -> specialist.getName().toLowerCase().contains(name))
                .collect(Collectors.toList());
    }

    public static <T,X> List<T> filterByType(List<X> all, Class<T> typeClass) {
        return all.stream().filter(typeClass::isInstance)
                .map(typeClass::cast)
                .collect(Collectors.toList());
    }
}
        