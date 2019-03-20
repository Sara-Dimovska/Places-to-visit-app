const { Place } = require('../models')

module.exports = {
  async post (req, res) {
    try {
      const restaurant = await Place.create(req.body)
      res.send(restaurant.toJSON())
    } catch (err) {
      res.status(500).send({
        error: 'An error has occured trying to create new restaurant.'
      })
    }
  },
  async get_restaurants (req, res) {
    try {
      const restaurants = await Place.findAll({
        where: {
          type: 'restaurant'
        },
        limit: 10
      })
      res.send(restaurants)
    } catch (err) {
      res.status(500).send({
        error: 'An error has occured trying to  fetch the restaurants.'
      })
    }
  }
}
