package com.fundatec.veterinaria.fixture;

import com.fundatec.veterinaria.application.request.CachorroRequest;

public class CachorroRequestFixture {

    private CachorroRequestFixture() {}

    public static CachorroRequest construirCachorroRequest() {
        CachorroRequest cachorroRequest = new CachorroRequest();
        cachorroRequest.setVeterinario(1l);
        cachorroRequest.setRaca("vira lata");
        cachorroRequest.setPeso(10);
        cachorroRequest.setNome("Bilu");
        cachorroRequest.setIdade(1);
        return cachorroRequest;
    }
}
