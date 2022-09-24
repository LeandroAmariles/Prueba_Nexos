package com.nexos.sistema_inventario_nexos.core.usecase.impl;

import com.nexos.sistema_inventario_nexos.config.exception.AuthenticationException;
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
            throw new CreateException("Dont can creates a new product, The user %s dont exist".formatted(id_usuario));
        }
        producto.setUser(usuarioRepository.findById(id_usuario).get());
        return productoRepository.save(producto);

    }

    @Override
    @Transactional
    public Producto actualizarProductoSiExiste(Producto producto, Long id, Long id_usuario) {
        productoRepository.findById(id)
                .map(PJpa -> {
                    Optional.ofNullable(producto.getCantidad()).ifPresent(PJpa::setCantidad);
                    Optional.ofNullable(producto.getFechaIngreso()).ifPresent(PJpa::setFechaIngreso);
                    if(PJpa != producto) {
                        PJpa.setUsuario_modifico(usuarioRepository.findById(id_usuario).get().getNombre());
                    }
                    return productoRepository.save(PJpa);
                }).orElseThrow(() -> new NotFoundException(id));

        return productoRepository.findById(id).get();
    }

    @Override
    @Transactional
    public void borrarProducto(Long id,Long id_usuario) {
        if(!AutenticarUsuario(id,id_usuario)){
            throw new AuthenticationException("The user with id %s is not allowed to do this".formatted(id_usuario));
        }
        productoRepository.findById(id).ifPresent(productoRepository::delete);

    }

    @Override
    @Transactional(readOnly = true)
    public ProductoList getList(PageRequest request) {
        Page<Producto> page = productoRepository.findAll(request);
        return new ProductoList(page.getContent(),request,page.getTotalElements());
    }

    @Override
    @Transactional(readOnly = true)
    public ProductoList getListNameFilter(PageRequest request, String nombre) {
        Page<Producto> page = new PageImpl<>(productoRepository.findAll(request).stream().filter(producto ->
                producto.getNombre().startsWith(nombre)).collect(Collectors.toList()));
        return new ProductoList(page.getContent(),request, page.getTotalElements());
    }

    @Override
    @Transactional(readOnly = true)
    public ProductoList getListNameUserFilter(PageRequest request, String nombre) {
        Page<Producto> page = new PageImpl<>(productoRepository.findAll(request).stream().filter(producto ->
                producto.getUser().getNombre().startsWith(nombre)).collect(Collectors.toList()));
        return new ProductoList(page.getContent(), request, page.getTotalElements());
    }

    @Override
    @Transactional(readOnly = true)
    public ProductoList getListDateFilter(PageRequest request, String fecha) {
        Page<Producto> page = new PageImpl<>(productoRepository.findAll(request).stream().filter(producto ->
                producto.getFechaIngreso().toString().substring(0,10).startsWith(fecha)).collect(Collectors.toList()));
        return new ProductoList(page.getContent(), request, page.getTotalElements());
    }

    private Boolean canCreate(String nombre, long id) {
        if (productoRepository.existsByNombre(nombre) || !usuarioRepository.existsById(id)) {
            throw new ConflictException("There is already a product with the name " + nombre +
                    " or the user with id " + id + " dont exists");
        }
        return true;
    }

    private Boolean AutenticarUsuario(Long id_producto, Long id_usuario){
        if(productoRepository.findById(id_producto).get().getUser().getNombre().equalsIgnoreCase(
                usuarioRepository.findById(id_usuario).get().getNombre()
        )){
            return true;
        }
        return false;
    }


}
