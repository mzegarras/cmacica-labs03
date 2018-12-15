package pe.cmacica.labs.labs03.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import pe.cmacica.labs.labs03.dominio.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ClienteRepositoryImpl implements ClienteRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    class ClienteMapper implements RowMapper<Cliente>{

        @Override
        public Cliente mapRow(ResultSet rs, int i) throws SQLException {
            Cliente cliente = new Cliente();

            cliente.setId(rs.getInt("id"));
            cliente.setNombres(rs.getString("nombres"));
            cliente.setPaterno(rs.getString("paterno"));
            cliente.setMaterno(rs.getString("materno"));
            cliente.setEdad(rs.getInt("edad"));
            cliente.setEmail(rs.getString("email"));
            return cliente;
        }
    }

    @Override
    public List<Cliente> listar() {

        return jdbcTemplate.query("select id,nombres,paterno,materno,edad,email from cliente", new ClienteMapper());

        /*
        List<Cliente> list =  new ArrayList<>();

        for(int i=0;i<=10;i++){
            Cliente c = new Cliente();
            c.setId(i);
            c.setNombres("Nombres" + i);
            list.add(c);
        }

        return list;*/
    }

    @Override
    public Cliente getCliente(int id) {

        Cliente c = jdbcTemplate.queryForObject("select id,nombres,paterno,materno from cliente where id=?",
                                            new Object[]{id},
                                            new ClienteMapper());
        /*Cliente c = new Cliente();
        c.setId(id);
        c.setNombres("Cliente");
        */
        return c;
    }

    @Override
    public int eliminar(int id) {

        return jdbcTemplate.update("delete from cliente where id=?",new Object[]{id});
        //return 0;
        //return 0;
        //return 0;
        //return 0;//return 0;

    }

    @Override
    public int update(Cliente cliente) {
        return jdbcTemplate.update("update cliente set nombres=?,paterno=?,materno=?,edad=?,email=? where id=?",
                        new Object[]{cliente.getNombres(),
                                    cliente.getPaterno(),
                                    cliente.getMaterno(),
                                    cliente.getId(),
                                    cliente.getEdad(),
                                    cliente.getEmail()});
    }

    @Override
    public void insert(Cliente cliente) {

        String SQL_INSERT = "insert into cliente(nombres,paterno,materno,edad,email) values (?,?,?,?,?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(
                new PreparedStatementCreator() {
                    @Override
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement ps = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);

                        ps.setString(1, cliente.getNombres());
                        ps.setString(2, cliente.getPaterno());
                        ps.setString(3, cliente.getMaterno());
                        ps.setInt(4, cliente.getEdad());
                        ps.setString(5, cliente.getEmail());

                        return ps;
                    }
                }, keyHolder);


        int newUserId = keyHolder.getKey().intValue();

        cliente.setId(newUserId);

    }

    @Override
    public void abonarCuenta(String cuenta, double monto) {

    }

    @Override
    public void debitarCuenta(String cuenta, double monto) {

    }


}
