package org.evander.lucena.services;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.evander.lucena.clients.BacenRestClient;
import org.evander.lucena.domain.dtos.TipoCotacaoDolarDTO;

import javax.cache.annotation.CacheResult;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

import static javax.transaction.Transactional.TxType.NOT_SUPPORTED;
import static org.evander.lucena.resources.core.config.LabelCache.*;

@ApplicationScoped
@Transactional(NOT_SUPPORTED)
public class CotacaoService {

    @Inject
    @RestClient
    BacenRestClient bacenRestClient;


    @CacheResult(cacheName = BACEN_COTACAO_DOLAR_DIA_CACHE)
    public List<TipoCotacaoDolarDTO> findCotacaoDolarDiaByDate(final String date) {
        final var cotacaoDolarDia = bacenRestClient.getCotacaoDolarDia(date);
        return cotacaoDolarDia.getValue();
    }

    @CacheResult(cacheName = BACEN_COTACAO_DOLAR_PERIODO_CACHE)
    public List<TipoCotacaoDolarDTO> findCotacaoDolarPeriodoByDataInicialAndDataFinal(final String dataInicial,
                                                                                      final String dataFinal) {

        final var cotacoes = bacenRestClient.getCotacaoDolarPeriodo(dataInicial, dataFinal);
        return cotacoes.getValue();
    }

}
