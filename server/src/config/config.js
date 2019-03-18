module.exports = {
  port: process.env.PORT || 8081,
  db: {
    database: process.env.DB_NAME || 'placestovisit',
    user: process.env.DB_USER || 'placestovisit',
    password: process.env.DB_PASS || 'placestovisit',
    options: {
      dialect: process.env.DIALECT || 'sqlite',
      host: process.env.HOST || '10.0.2.2',
      storage: './placestovisit.sqlite',
      operatorsAliases: false
    }
  },
  authentication: {
    jwtSecret: process.env.JWT_SECRET || 'secret'
  }
}
