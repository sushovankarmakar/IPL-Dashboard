import React from "react";

export const MatchDetailsCard = ({ match }) => {
  if (!match) return null;

  const { homeTeam, awayTeam, date } = match;

  return (
    <div className="MatchDetailsCard">
      <h3>Latest Matches</h3>
      <h4>Match Details</h4>
      <h3>
        {homeTeam} vs {awayTeam} on {date}
      </h3>
    </div>
  );
};
