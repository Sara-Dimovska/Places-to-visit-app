const AuthenticationController = require('./controllers/AuthenticationController')
const AuthenticationControllerPolicy = require('./policies/AuthenticationControllerPolicy')
const RestaurantsController = require('./controllers/RestaurantsController')

module.exports = (app) => {
  app.post('/register',
    AuthenticationControllerPolicy.register,
    AuthenticationController.register
  )
  app.post('/login',
    AuthenticationController.login
  )

  app.get('/get_restaurants',
    RestaurantsController.get_restaurants
  )
  app.post('/create_restorant',
    RestaurantsController.post
  )
}
