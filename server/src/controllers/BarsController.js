const { Place } = require('../models')

module.exports = {
  async post (req, res) {
    try {
      const bar = await Place.create(req.body)
      res.send(bar.toJSON())
    } catch (err) {
      res.status(500).send({
        error: 'An error has occured trying to create new bar.'
      })
    }
  },
  async get_bars (req, res) {
    try {
      const bars = await Place.findAll({
        where: {
          type: 'bar'
        },
        limit: 10
      })
      res.send(bars)
    } catch (err) {
      res.status(500).send({
        error: 'An error has occured trying to  fetch the bar.'
      })
    }
  }
}
