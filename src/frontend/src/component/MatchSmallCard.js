import React from "react";

export const MatchSmallCard = ({ match }) => {
  if (!match) return null;

  const { homeTeam, awayTeam, date } = match;

  return (
    <div className="MatchSmallCard">
      <h5>
        {homeTeam} vs {awayTeam} on {date}
      </h5>
    </div>
  );
};
