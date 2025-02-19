package fr.dawan.demoapirest.tools;

import org.modelmapper.ModelMapper;




public class ModelMapperDemo {

    private static ModelMapper mapper = new ModelMapper();

    public static ContactDto dtoFormEntity(Contact c){
        //return mapper.map(c, ContactDto.class);

        mapper.typeMap(Contact.class, ContactDto.class)

                .addMappings(m ->
                        {
                            m.map(src -> src.getFirstName(), ContactDto::setNom);
                            m.map(src -> src.getLastName(), ContactDto::setPrenom);
                        }

                        );

        return mapper.map(c, ContactDto.class);
    }
}
