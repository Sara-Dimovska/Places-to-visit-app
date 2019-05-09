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
  },
  async get_place (req, res) {
    try {
      const restaurant = await Place.findOne({
        where: { id: req.params.id }
      })
      res.send(restaurant)
    } catch (err) {
      res.status(500).send({
        error: 'An error has occured trying to  fetch the place.'
      })
    }
  },
  async delete_place (req, res) {
    try {
      const success = await Place.destroy({
        where: { id: req.params.id }
      })
      console.log('Success', success)
      res.sendStatus(200)
    } catch (err) {
      res.status(500).send({
        error: 'An error has occured trying to  delete the place.'
      })
    }
  }
}
