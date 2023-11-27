package com.movementssapi.accounts.service;

import com.movementssapi.accounts.dto.ClienteDTO;
import com.movementssapi.accounts.dto.ResponseDTO;
import com.movementssapi.accounts.model.Cuenta;
import com.movementssapi.accounts.repository.CuentaRepository;
import com.movementssapi.accounts.repository.MovimientoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class CuentaServiceImpl implements CuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private MovimientoRepository movimientoRepository;

    private final RestTemplate restTemplate;

    public CuentaServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Override
    public Cuenta crearCuenta(Cuenta cuentaSave) {

        return cuentaRepository.save(cuentaSave);
    }

    @Override
    public Optional<Cuenta> obtenerCuentaPorNumero(String numeroCuenta) {
        return cuentaRepository.findByNumeroCuenta(numeroCuenta);
    }

    @Override
    public boolean existeCuentaPorId(Integer id) {
        return cuentaRepository.existsById(id);
    }
    @Override
    public Optional<Cuenta> obtenerCuentaPorId(Integer id) {
        return cuentaRepository.findById(id);
    }

    @Override
    public List<Cuenta> getAllCuentas() {
        return cuentaRepository.findAll();
    }

    @Override
    public Cuenta updateCuenta(Cuenta cuenta) {
        return cuentaRepository.save(cuenta);
    }

    @Override
    public void eliminarCuenta(Integer id) {
        cuentaRepository.deleteById(id);
    }

    @Override
    public List<Cuenta> getCuentasPorCliente(Integer clienteId) {
        return cuentaRepository.findAll();
    }

    @Override
    public ResponseDTO getClientebyId(Integer id){
        ResponseDTO responseDTO = new ResponseDTO();
        Cuenta cuenta = new Cuenta();

        cuenta = cuentaRepository.findById(id).get();

        ResponseEntity<ClienteDTO> responseEntity = restTemplate.getForEntity("http://localhost:8081/api/clientes/" + cuenta.getId(),
                ClienteDTO.class);

        ClienteDTO clienteDTO = responseEntity.getBody();
        responseDTO.setCuenta(cuenta);
        responseDTO.setCliente(clienteDTO);

        return responseDTO;

    }

}

