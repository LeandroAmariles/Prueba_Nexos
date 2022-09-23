package com.nexos.sistema_inventario_nexos.core.model;

import com.nexos.sistema_inventario_nexos.core.model.Usuario;
import com.nexos.sistema_inventario_nexos.core.model.audit.Audit;
import com.nexos.sistema_inventario_nexos.core.model.audit.AuditListener;
import com.nexos.sistema_inventario_nexos.core.model.audit.Auditable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@ToString
@NoArgsConstructor
@Entity
@Table(name="cargo")
@Where(clause = "is_active=true")
@SQLDelete(sql = "UPDATE cargo SET is_active=false WHERE cargo_id=? ")
@EntityListeners(AuditListener.class)
public class Cargo implements Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cargo_id")
    private Long id;

    @Column(name="nombre", nullable = false)
    private String nombre;

    @Embedded
    private Audit audit;


}
