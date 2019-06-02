const { Place } = require('../models')
const Sequelize = require('sequelize')
const { Rating } = require('../models')

module.exports = {
  async get_bars (req, res) {
    try {
      let bars = await Place.findAll({
        where: {
          type: 'bar'
        }
      })
      for (let bar of bars) {
        const result = await Rating.findAll({
          raw: true,
          group: ['place_id'],
          attributes: [[Sequelize.fn('avg', Sequelize.col('rating')), 'avgRating']],
          where: { place_id: bar.id }
        })
        if (result.length > 0) {
          bar.avgRating = Math.round(result[0].avgRating)
        }
      }
      res.send(bars)
    } catch (err) {
      res.status(500).send({
        error: 'An error has occured trying to  fetch the bars.'
      })
    }
  },
  async search_bars (req, res) {
    try {
      const Op = Sequelize.Op
      const places = await Place.findAll({
        where: { name: { [Op.like]: '%' + req.query.name + '%' }, type: 'bar' }
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
