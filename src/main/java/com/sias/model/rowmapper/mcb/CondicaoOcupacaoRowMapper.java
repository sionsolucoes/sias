/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sias.model.rowmapper.mcb;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.sias.model.constants.mcb.CondicaoOcupacaoConstants;
import com.sias.model.entity.mcb.CondicaoOcupacao;

/**
 *
 * @author Fernando Laranjo
 */
public class CondicaoOcupacaoRowMapper implements RowMapper<CondicaoOcupacao> {

    @Override
    public CondicaoOcupacao mapRow(ResultSet rs, int i) throws SQLException {

        CondicaoOcupacao condicaoOcupacao = new CondicaoOcupacao();

        condicaoOcupacao.setId(rs.getLong(CondicaoOcupacaoConstants.ID));
        condicaoOcupacao.setCodigo(rs.getShort(CondicaoOcupacaoConstants.CODIGO));
        condicaoOcupacao.setDescricao(rs.getString(CondicaoOcupacaoConstants.DESCRICAO));

        return condicaoOcupacao;
    }

}
