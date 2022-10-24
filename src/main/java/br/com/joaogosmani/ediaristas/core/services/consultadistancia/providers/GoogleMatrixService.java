package br.com.joaogosmani.ediaristas.core.services.consultadistancia.providers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.DistanceMatrixElementStatus;

import br.com.joaogosmani.ediaristas.core.services.consultadistancia.adapters.ConsultaDistanciaService;
import br.com.joaogosmani.ediaristas.core.services.consultadistancia.dtos.DistanciaResponse;
import br.com.joaogosmani.ediaristas.core.services.consultadistancia.exceptions.ConsultaDistanciaServiceException;

@Service
public class GoogleMatrixService implements ConsultaDistanciaService {

    @Value("${br.com.joaogosmani.ediaristas.googleMatrix.apiKey}")
    private String apiKey;

    @Override
    public DistanciaResponse calcularDistanciaEntreDoisCeps(String origem, String destino) {
        var origemFormatada = formatarCep(origem);
        var destinoFormatado = formatarCep(destino);

        var distanceMatrixApiRequest = getDistanceMatrixApiRequest();
        var distanceMatrix = getDistanceMatrix(origemFormatada, destinoFormatado, distanceMatrixApiRequest);

        return distanceMatrixToDistanciaResponse(distanceMatrix);
    }

    private DistanciaResponse distanceMatrixToDistanciaResponse(DistanceMatrix distanceMatrix) {
        validarDistanceMatrix(distanceMatrix);

        return DistanciaResponse.builder()
            .origem(distanceMatrix.originAddresses[0])
            .destino(distanceMatrix.destinationAddresses[0])
            .distanciaEmKm(distanceMatrix.rows[0].elements[0].distance.inMeters / 1000.0)
            .build();
    }

    private DistanceMatrix getDistanceMatrix(String origem, String destino, DistanceMatrixApiRequest request) {
        try {
            return request.origins(origem)
                .destinations(destino)
                .await();
        } catch (ApiException | InterruptedException | IOException exception) {
            throw new ConsultaDistanciaServiceException(exception.getLocalizedMessage());
        }
    }

    private DistanceMatrixApiRequest getDistanceMatrixApiRequest() {
        return DistanceMatrixApi.newRequest(getGeoApiContext());
    }

    private GeoApiContext getGeoApiContext() {
        return new GeoApiContext.Builder()
            .apiKey(apiKey)
            .build();
    }

    private void validarDistanceMatrix(DistanceMatrix distanceMatrix) {
        var rows = distanceMatrix.rows;
        if (rows.length == 0) {
            throw new ConsultaDistanciaServiceException("Erro ao realizar a consulta");
        }

        var elements = rows[0].elements;
        if (elements.length == 0) {
            throw new ConsultaDistanciaServiceException("Erro ao realizar a consulta");
        }

        var status = elements[0].status;
        if (!status.equals(DistanceMatrixElementStatus.OK)) {
            throw new ConsultaDistanciaServiceException("Erro ao realizar a consulta");
        }
    }

    private String formatarCep(String cep) {
        validarCep(cep);

        var stringBuilder = new StringBuilder(cep);
        stringBuilder.insert(5, "-");
        return stringBuilder.toString();
    }

    private void validarCep(String cep) {
        if (cep.length() != 8) {
            throw new IllegalArgumentException("O cep deve ter oito dígitos");
        }

        if (!cep.matches("[0-9]+")) {
            throw new IllegalArgumentException("O cep deve ter apenas números");
        }
    }
    
}
