package com.nexos.sistema_inventario_nexos.core.model;

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

import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Set;

@Setter
@Getter
@ToString
@NoArgsConstructor
@Entity
@Table(name="usuario")
@Where(clause = "is_active=true")
@SQLDelete(sql = "UPDATE usuario SET is_active=false WHERE usuario_id=? ")
@EntityListeners(AuditListener.class)
public class Usuario implements Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name="nombre", nullable = false, updatable = false)
    private String nombre;

    @Column(name="edad", nullable = false, updatable = false)
    private Integer edad;

    @Column(name = "fecha_ingreso", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime fechaIngreso;

    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "cargo_id", referencedColumnName = "cargo_id", nullable = false)
    @ToString.Exclude
    private Cargo cargo;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<Producto> productos;


    @Embedded
    private Audit audit;

}
