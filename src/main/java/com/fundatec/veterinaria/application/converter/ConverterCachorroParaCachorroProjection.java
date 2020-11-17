package com.fundatec.veterinaria.application.converter;

import com.fundatec.veterinaria.application.projection.CachorroProjection;
import com.fundatec.veterinaria.domain.Cachorro;
import org.springframework.stereotype.Component;

@Component
public class ConverterCachorroParaCachorroProjection {

    public CachorroProjection converter(Cachorro cachorro) {
        return new CachorroProjection(
                cachorro.getNome(),
                cachorro.getVeterinario().getNome()
        );
    }

}
