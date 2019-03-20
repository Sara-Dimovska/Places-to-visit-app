const { Place } = require('../models')

module.exports = {
  async post (req, res) {
    try {
      const nightclub = await Place.create(req.body)
      res.send(nightclub.toJSON())
    } catch (err) {
      res.status(500).send({
        error: 'An error has occured trying to create new nightclub.'
      })
    }
  },
  async get_bars (req, res) {
    try {
      const nightclubs = await Place.findAll({
        where: {
          type: 'nightclub'
        },
        limit: 10
      })
      res.send(nightclubs)
    } catch (err) {
      res.status(500).send({
        error: 'An error has occured trying to  fetch the nightclubs.'
      })
    }
  }
}
