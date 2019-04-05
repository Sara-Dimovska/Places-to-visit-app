const { Place } = require('../models')

module.exports = {
  async get_nightclubs (req, res) {
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
