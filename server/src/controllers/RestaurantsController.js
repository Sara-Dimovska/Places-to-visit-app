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
  },
  async delete_restaurants (req, res) {
    try {
      const success = await Place.destory({
        where: { id: req.params.id }
      })
      console.log('Success', success)
      res.sendStatus(200)
    } catch (err) {
      res.status(500).send({
        error: 'An error has occured trying to  delete the restaurant.'
      })
    }
  }
}
