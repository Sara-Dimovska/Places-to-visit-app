const { Place } = require('../models')
const Sequelize = require('sequelize')
const { Rating } = require('../models')

module.exports = {
  async get_restaurants (req, res) {
    try {
      let restaurants = await Place.findAll({
        where: {
          type: 'restaurant'
        }
      })
      for (let restaurant of restaurants) {
        const result = await Rating.findAll({
          raw: true,
          group: ['place_id'],
          attributes: [[Sequelize.fn('avg', Sequelize.col('rating')), 'avgRating']],
          where: { place_id: restaurant.id }
        })
        if (result.length > 0) {
          restaurant.avgRating = Math.round(result[0].avgRating)
        }
      }
      res.send(restaurants)
    } catch (err) {
      res.status(500).send({
        error: 'An error has occured trying to  fetch the restaurants.'
      })
    }
  }
}
