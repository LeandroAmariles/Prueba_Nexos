package com.nexos.sistema_inventario_nexos.core.model.audit;

public interface Auditable {

    Audit getAudit();

    void setAudit(Audit audit);
}
