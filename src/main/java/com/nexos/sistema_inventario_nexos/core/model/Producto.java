package com.nexos.sistema_inventario_nexos.core.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.nexos.sistema_inventario_nexos.core.model.audit.Audit;
import com.nexos.sistema_inventario_nexos.core.model.audit.AuditListener;
import com.nexos.sistema_inventario_nexos.core.model.audit.Auditable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;


@Setter
@Getter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "producto")
@Where(clause = "is_active=true")
@SQLDelete(sql = "UPDATE producto SET is_active=false WHERE producto_id=? ")
@EntityListeners(AuditListener.class)
public class Producto implements Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "producto_id")
    private Long id;

    @Column(name = "nombre_producto", nullable = false)
    private String nombre;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @Column(name = "fecha_ingreso", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime fechaIngreso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id",nullable = false)
    @ToString.Exclude
    private Usuario user;

    @Column(name = "usuario_modifico", nullable = true)
    private String usuario_modifico;

    @Embedded
    private Audit audit;


}
