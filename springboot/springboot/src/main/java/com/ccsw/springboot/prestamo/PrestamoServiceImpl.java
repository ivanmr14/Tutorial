package com.ccsw.springboot.prestamo;

import java.time.temporal.ChronoUnit;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ccsw.springboot.client.ClientService;
import com.ccsw.springboot.common.criteria.SearchCriteria;
import com.ccsw.springboot.game.GameService;
import com.ccsw.springboot.prestamo.model.Prestamo;
import com.ccsw.springboot.prestamo.model.PrestamoDto;
import com.ccsw.springboot.prestamo.model.PrestamoSearchDto;

import jakarta.transaction.Transactional;

/**
 * @author ccsw
 *
 */
@Service
@Transactional
public class PrestamoServiceImpl implements PrestamoService {

    @Autowired
    PrestamoRepository prestamoRepository;

    @Autowired
    ClientService clientService;

    @Autowired
    GameService gameService;

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<Prestamo> findPage(PrestamoSearchDto dto) {

        PrestamoSpecification titleSpec = new PrestamoSpecification(
                new SearchCriteria("game.id", ":", dto.getIdJuego()));
        PrestamoSpecification nameSpec = new PrestamoSpecification(
                new SearchCriteria("client.id", ":", dto.getIdCliente()));

        PrestamoSpecification dateSpec = new PrestamoSpecification(
                new SearchCriteria("fechaComienzo", "fecha", dto.getFecha()));

        Specification<Prestamo> spec = Specification.where(titleSpec).and(nameSpec).and(dateSpec);

        return this.prestamoRepository.findAll(spec, dto.getPageable().getPageable());

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Long id, PrestamoDto data) {

        Prestamo prestamo;

        prestamo = new Prestamo();

        BeanUtils.copyProperties(data, prestamo, "id", "game", "client");

        prestamo.setClient(clientService.get(data.getClient().getId()));
        prestamo.setGame(gameService.get(data.getGame().getId()));
        prestamo.setFechaComienzo(data.getFechaComienzo().plusDays(1));
        prestamo.setFechaDevolucion(data.getFechaDevolucion().plusDays(1));

        if (ChronoUnit.DAYS.between(prestamo.getFechaComienzo(), prestamo.getFechaDevolucion()) > 14) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "El rango de fecha del préstamo es superior al permitido.");
        }

        boolean comprobacionPrestamoJuego = prestamoRepository
                .existsByGameAndFechaComienzoLessThanEqualAndFechaDevolucionGreaterThanEqual(prestamo.getGame(),
                        prestamo.getFechaDevolucion(), prestamo.getFechaComienzo());

        if (comprobacionPrestamoJuego == true) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El juego ya se encuentra en préstamo.");
        }

        boolean comprobacionPrestamosCliente = prestamoRepository
                .existsByClientAndFechaComienzoLessThanEqualAndFechaDevolucionGreaterThanEqual(prestamo.getClient(),
                        prestamo.getFechaDevolucion(), prestamo.getFechaComienzo());

        if (comprobacionPrestamosCliente == true) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El cliente ya tiene un préstamo activo.");
        } else {
            this.prestamoRepository.save(prestamo);
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long id) throws Exception {

        if (this.prestamoRepository.findById(id).orElse(null) == null) {
            throw new Exception("Not exists");
        }

        this.prestamoRepository.deleteById(id);
    }

    @Override
    public Prestamo get(Long id) {
        return this.prestamoRepository.findById(id).orElse(null);
    }

}
