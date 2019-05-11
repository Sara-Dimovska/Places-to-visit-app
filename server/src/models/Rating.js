module.exports = (sequalize, DataTypes) => {
  const Rating = sequalize.define('Rating', {
    user_id: DataTypes.INTEGER,
    place_id: DataTypes.INTEGER,
    rating: DataTypes.INTEGER
  })
  return Rating
}
