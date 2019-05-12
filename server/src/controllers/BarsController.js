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
  }
}
