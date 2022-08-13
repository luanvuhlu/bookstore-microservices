package com.luanvv.microservices.bookstore.product_import.configuration

import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy
import org.hibernate.boot.model.naming.Identifier
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy

class UpperPhysicalNamingStrategy : CamelCaseToUnderscoresNamingStrategy() {
    override fun getIdentifier(
        name: String,
        quoted: Boolean,
        jdbcEnvironment: JdbcEnvironment
    ): Identifier {
        var name = name
        if (isCaseInsensitive(jdbcEnvironment)) {
            name = name.uppercase()
        }
        return Identifier(name, quoted)
    }
}