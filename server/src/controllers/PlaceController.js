const { Place } = require('../models')

module.exports = {
  async post (req, res) {
    try {
      const place = await Place.create(req.body)
      res.send(place.toJSON())
    } catch (err) {
      res.status(500).send({
        error: 'An error has occured trying to create new place.'
      })
    }
  },
  async get_place (req, res) {
    try {
      const place = await Place.findOne({
        where: { id: req.params.id }
      })
      res.send(place)
    } catch (err) {
      res.status(500).send({
        error: 'An error has occured trying to  fetch the place.'
      })
    }
  },
  async get_places (req, res) {
    try {
      const places = await Place.findAll({
        limit: 10
      })
      res.send(places)
    } catch (err) {
      res.status(500).send({
        error: 'An error has occured trying to  fetch the places.'
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
