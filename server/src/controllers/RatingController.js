const { Rating } = require('../models')

module.exports = {
  async post (req, res) {
    try {
      const rate = await Rating.create(req.body)
      res.send(rate.toJSON())
    } catch (err) {
      res.status(500).send({
        error: 'An error has occured trying to add a rating.'
      })
    }
  },
  async get_ratings (req, res) {
    try {
      const ratings = await Rating.findAll({
      })
      res.send(ratings)
    } catch (err) {
      res.status(500).send({
        error: 'An error has occured trying to  fetch the ratings.'
      })
    }
  }
}
