package com.nexos.sistema_inventario_nexos.core.usecase.impl;

import com.nexos.sistema_inventario_nexos.config.exception.ConflictException;
import com.nexos.sistema_inventario_nexos.config.exception.CreateException;
import com.nexos.sistema_inventario_nexos.config.exception.NotFoundException;
import com.nexos.sistema_inventario_nexos.core.model.Producto;
import com.nexos.sistema_inventario_nexos.core.model.ProductoList;
import com.nexos.sistema_inventario_nexos.core.repository.ProductoRepository;
import com.nexos.sistema_inventario_nexos.core.repository.UsuarioRepository;
import com.nexos.sistema_inventario_nexos.core.usecase.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;
    private final UsuarioRepository usuarioRepository;

    @Override
    @Transactional
    public Producto crearNuevoProducto(Producto producto, Long id_usuario) {
        if (!canCreate(producto.getNombre(), id_usuario)) {
            throw new CreateException("Dont can creates a new product");
        }
        producto.setUser(usuarioRepository.findById(id_usuario).get());
        return productoRepository.save(producto);

    }

    @Override
    @Transactional
    public Producto actualizarProductoSiExiste(Producto producto, Long id) {
        productoRepository.findById(id)
                .map(PJpa -> {
                    Optional.ofNullable(producto.getCantidad()).ifPresent(PJpa::setCantidad);
                    Optional.ofNullable(producto.getFechaIngreso()).ifPresent(PJpa::setFechaIngreso);
                    return productoRepository.save(PJpa);
                }).orElseThrow(() -> new NotFoundException(id));

        return productoRepository.findById(id).get();
    }

    @Override
    @Transactional
    public void borrarProducto(Long id) {
        productoRepository.findById(id).ifPresent(productoRepository::delete);

    }

    @Override
    public ProductoList getList(PageRequest request) {
        Page<Producto> page = productoRepository.findAll(request);
        return new ProductoList(page.getContent(),request,page.getTotalElements());
    }

    @Override
    public ProductoList getListNameFilter(PageRequest request, String nombre) {
        Page<Producto> page = new PageImpl<>(productoRepository.findAll(request).stream().filter(producto ->
                producto.getNombre().equalsIgnoreCase(nombre)).collect(Collectors.toList()));
        return new ProductoList(page.getContent(),request, page.getTotalElements());
    }

    @Override
    public ProductoList getListNameUserFilter(PageRequest request, String nombre) {
        Page<Producto> page = new PageImpl<>(productoRepository.findAll(request).stream().filter(producto ->
                producto.getUser().getNombre().equalsIgnoreCase(nombre)).collect(Collectors.toList()));
        return new ProductoList(page.getContent(), request, page.getTotalElements());
    }

    private Boolean canCreate(String nombre, long id) {
        if (productoRepository.existsByNombre(nombre) || !usuarioRepository.existsById(id)) {
            throw new ConflictException("There is already a product with the name " + nombre +
                    " or the user with id " + id + " dont exists");
        }
        return true;
    }


}
