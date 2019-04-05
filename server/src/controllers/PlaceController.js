const { Place } = require('../models')

module.exports = {
  async post (req, res) {
    try {
      const restaurant = await Place.create(req.body)
      res.send(restaurant.toJSON())
    } catch (err) {
      res.status(500).send({
        error: 'An error has occured trying to create new place.'
      })
    }
  }
}
