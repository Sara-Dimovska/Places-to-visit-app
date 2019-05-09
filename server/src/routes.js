const AuthenticationController = require('./controllers/AuthenticationController')
const AuthenticationControllerPolicy = require('./policies/AuthenticationControllerPolicy')
const RestaurantsController = require('./controllers/RestaurantsController')
const BarsController = require('./controllers/BarsController')
const NightclubsController = require('./controllers/NightclubsController')
const PlaceController = require('./controllers/PlaceController')

module.exports = (app) => {
  app.post('/register',
    AuthenticationControllerPolicy.register,
    AuthenticationController.register
  )
  app.post('/login',
    AuthenticationController.login
  )

  app.post('/create_place',
    PlaceController.post
  )
  app.get('/get_place/:id',
    PlaceController.get_place
  )

  app.get('/get_restaurants',
    RestaurantsController.get_restaurants
  )
  app.delete('/delete_restorant/:id',
    RestaurantsController.delete_restaurants
  )

  app.get('/get_bars',
    BarsController.get_bars
  )

  app.get('/get_nightclubs',
    NightclubsController.get_nightclubs
  )
}
