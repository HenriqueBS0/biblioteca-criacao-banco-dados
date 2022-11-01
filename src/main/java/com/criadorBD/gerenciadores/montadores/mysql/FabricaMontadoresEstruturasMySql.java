package com.criadorBD.gerenciadores.montadores.mysql;

import com.criadorBD.gerenciadores.montadores.interfaces.IFabricaMontadoresEstruturas;
import com.criadorBD.gerenciadores.montadores.interfaces.IMontadorBaseDados;
import com.criadorBD.gerenciadores.montadores.interfaces.IMontadorTabela;

public class FabricaMontadoresEstruturasMySql implements IFabricaMontadoresEstruturas {
    public IMontadorBaseDados montadorBaseDados() {
        return new MontadorBaseDadosMySql();
    }

    public IMontadorTabela montadorTabela() {
        return new MontadorTabelaMySql();
    }    
}
