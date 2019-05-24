const { Place } = require('../models')
const Sequelize = require('sequelize')
const { Rating } = require('../models')

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
      })
      res.send(places)
    } catch (err) {
      res.status(500).send({
        error: 'An error has occured trying to  fetch the places.'
      })
    }
  },
  async search_places (req, res) {
    try {
      const Op = Sequelize.Op
      const places = await Place.findAll({
        where: { name: { [Op.like]: '%' + req.query.name + '%' } }
      })
      for (let place of places) {
        const result = await Rating.findAll({
          raw: true,
          group: ['place_id'],
          attributes: [[Sequelize.fn('avg', Sequelize.col('rating')), 'avgRating']],
          where: { place_id: place.id }
        })
        if (result.length > 0) {
          place.avgRating = Math.round(result[0].avgRating)
        }
      }
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
  },
  async edit_place (req, res) {
    try {
      const result = await Place.update(req.body, {
        where: { id: req.params.id }
      })
      console.log(result)
      res.sendStatus(200)
    } catch (err) {
      res.status(500).send({
        error: 'An error has occured trying to  edit the place.'
      })
    }
  }
}
