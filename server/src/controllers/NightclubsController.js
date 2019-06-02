const { Place } = require('../models')
const Sequelize = require('sequelize')
const { Rating } = require('../models')

module.exports = {
  async get_nightclubs (req, res) {
    try {
      let nightclubs = await Place.findAll({
        where: {
          type: 'nightclub'
        }
      })
      for (let nightclub of nightclubs) {
        const result = await Rating.findAll({
          raw: true,
          group: ['place_id'],
          attributes: [[Sequelize.fn('avg', Sequelize.col('rating')), 'avgRating']],
          where: { place_id: nightclub.id }
        })
        if (result.length > 0) {
          nightclub.avgRating = Math.round(result[0].avgRating)
        }
      }
      res.send(nightclubs)
    } catch (err) {
      res.status(500).send({
        error: 'An error has occured trying to  fetch the nightclubs.'
      })
    }
  },
  async search_nightclubs (req, res) {
    try {
      const Op = Sequelize.Op
      const places = await Place.findAll({
        where: { name: { [Op.like]: '%' + req.query.name + '%' }, type: 'nightclub' }
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
  }
}
