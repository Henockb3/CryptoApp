package crypto.dao;

import crypto.entity.Investment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class InvestmentDaoImpl implements InvestmentDao{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public static final class investmentMapper implements RowMapper<Investment> {
        public Investment mapRow(ResultSet resultSet, int i) throws SQLException {
            Investment investments=new Investment();
            investments.setInvestmentId(resultSet.getInt("investmentId"));
            investments.setPortfolioId(resultSet.getInt("portfolioId"));
            investments.setCryptoName(resultSet.getString("cryptoName"));
            investments.setInvestedAmount(resultSet.getBigDecimal("investedAmount"));
            investments.setShares(resultSet.getBigDecimal("shares"));
            return investments;
        }
    }

    @Override
    public List<Investment> getAllInvestments(int portfolioId) throws DataAccessException {
        final String SELECT_Investment = "SELECT * FROM Investment where portfolioId=?;";
        return jdbcTemplate.query(SELECT_Investment, new investmentMapper(),portfolioId);
    }
}
