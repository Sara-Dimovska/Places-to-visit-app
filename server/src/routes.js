const AuthenticationController = require('./controllers/AuthenticationController')
const AuthenticationControllerPolicy = require('./policies/AuthenticationControllerPolicy')
const RestaurantsController = require('./controllers/RestaurantsController')
const BarsController = require('./controllers/BarsController')
const NightclubsController = require('./controllers/NightclubsController')

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

  app.get('/get_bars',
    BarsController.get_bars
  )
  app.post('/create_bar',
    BarsController.post
  )

  app.get('/get_nightclubs',
    NightclubsController.get_bars
  )
  app.post('/create_nightclub',
    NightclubsController.post
  )
}
