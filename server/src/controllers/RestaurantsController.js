const { Place } = require('../models')

module.exports = {
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
