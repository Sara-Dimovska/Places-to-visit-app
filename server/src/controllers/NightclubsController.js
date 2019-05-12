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
  }
}
