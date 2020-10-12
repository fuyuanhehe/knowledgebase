package com.hg.knowledgebase.config.db;

import java.io.IOException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
@MapperScan(basePackages = "com.hg.institute.mapper", sqlSessionTemplateRef = "pgSqlSessionTemplate")
public class DataSourceConfigPgSql
{
    @Bean(name = "pgsql_ds")
    @Qualifier("pgsql_ds")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource pgDataSource()
    {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "pgsqlJdbcTemplate")
    public JdbcTemplate pgJdbcTemplate(
            @Qualifier("pgsql_ds") DataSource dataSource)
    {
        return new JdbcTemplate(dataSource);
    }

    @Qualifier("pgsqlTransactionManager")
    @Bean(name = "pgsqlTransactionManager")
    public DataSourceTransactionManager pgTransactionManager(
            @Qualifier("pgsql_ds") DataSource pgDataSource)
    {
        DataSourceTransactionManager pgTransactionManager = new DataSourceTransactionManager();
        pgTransactionManager.setDataSource(pgDataSource);
        return pgTransactionManager;
    }

    @Bean(name = "pgSqlSessionFactory")
    @Qualifier("pgSqlSessionFactory")
    public SqlSessionFactory pgSqlSessionFactory(
            @Qualifier("pgsql_ds") DataSource pgDataSource) throws Exception
    {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        SqlSessionFactoryBean pgSqlSessionFactory = new SqlSessionFactoryBean();
        pgSqlSessionFactory.setDataSource(pgDataSource);
        try
        {
            pgSqlSessionFactory.setMapperLocations(
                    resolver.getResources("classpath*:mapper/*/*.xml"));
            // 指定VFS确保可以扫描到实体类
            pgSqlSessionFactory.setVfs(SpringBootVFS.class);
            pgSqlSessionFactory.setTypeAliasesPackage(
                    "com.hg.knowledgebase.module.sys.entity;"
                            + "com.hg.knowledgebase.module.sys.entity.VO;"
                            + "com.hg.knowledgebase.module.act.entity;"
                            + "com.hg.knowledgebase.module.act.entity.VO;"
                            + "com.hg.knowledgebase.module.community.entity;"
                            + "com.hg.knowledgebase.module.community.entity.VO;"
                            + "com.hg.knowledgebase.module.eval.entity;"
                            + "com.hg.knowledgebase.module.eval.entity.VO;"
                            + "com.hg.knowledgebase.module.proj.entity;"
                            + "com.hg.knowledgebase.module.proj.entity.VO;"
                            + "com.hg.knowledgebase.module.repo.entity;"
                            + "com.hg.knowledgebase.module.repo.entity.VO;"
                            + "com.hg.knowledgebase.module.stat.entity;"
                            + "com.hg.knowledgebase.module.stat.entity.VO;"
                            + "com.hg.knowledgebase.module.global.bean;" + "");
            return pgSqlSessionFactory.getObject();
        } catch (IOException e)
        {
            throw new Exception(e);
        }
    }

    @Bean(name = "pgSqlSessionTemplate")
    public SqlSessionTemplate pgSqlSessionTemplate(
            @Qualifier("pgSqlSessionFactory") SqlSessionFactory pgSqlSessionFactory)
    {
        return new SqlSessionTemplate(pgSqlSessionFactory);
    }
}
