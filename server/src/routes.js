const AuthenticationController = require('./controllers/AuthenticationController')
const AuthenticationControllerPolicy = require('./policies/AuthenticationControllerPolicy')
const RestaurantsController = require('./controllers/RestaurantsController')
const BarsController = require('./controllers/BarsController')
const NightclubsController = require('./controllers/NightclubsController')
const PlaceController = require('./controllers/PlaceController')
const RatingController = require('./controllers/RatingController')

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
  app.get('/get_places',
    PlaceController.get_places
  )
  app.get('/search_places',
    PlaceController.search_places
  )
  app.delete('/delete_place/:id',
    PlaceController.delete_place
  )
  app.post('/edit_place/:id',
    PlaceController.edit_place
  )

  app.get('/get_restaurants',
    RestaurantsController.get_restaurants
  )

  app.get('/get_bars',
    BarsController.get_bars
  )

  app.get('/get_nightclubs',
    NightclubsController.get_nightclubs
  )

  app.post('/add_rating',
    RatingController.post
  )
  app.get('/get_ratings',
    RatingController.get_ratings
  )
}
