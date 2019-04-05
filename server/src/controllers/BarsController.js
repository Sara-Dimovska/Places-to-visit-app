const { Place } = require('../models')

module.exports = {
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
