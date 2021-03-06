package org.evander.lucena.resources.impl;


import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.evander.lucena.resources.ICotacaoResource;
import org.evander.lucena.resources.config.BaseResource;
import org.evander.lucena.services.CotacaoService;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.OK;


public class CotacaoResourceImpl extends BaseResource implements ICotacaoResource {

    @Inject
    CotacaoService cotacaoService;

    @Override
    @Timed(name = "timed/cotacao-dolar-dia")
    @Counted(name = "counted/cotacao-dolar-dia")
    public Response findCotacaoDolarDiaByDate(final String dataCotacao) {
        final var cotacaoDolarDia = cotacaoService.findCotacaoDolarDiaByDate(dataCotacao);
        return toResponse(OK, cotacaoDolarDia);
    }

    @Override
    @Timed(name = "timed/cotacao-dolar-periodo")
    @Counted(name = "counted/cotacao-dolar-periodo")
    public Response findCotacaoDolarPeriodoByDataInicialAndDataFinal(final String dataInicial, final String dataFinal) {
        final var cotacaoDolarPeriodo = cotacaoService.findCotacaoDolarPeriodoByDataInicialAndDataFinal(dataInicial, dataFinal);
        return toResponse(OK, cotacaoDolarPeriodo);
    }


}
