import React from "react";
import { Link } from "react-router-dom";

export const MatchDetailsCard = ({ name, match }) => {
  if (!match) return null;

  const {
    homeTeam,
    awayTeam,
    date,
    venue,
    city,
    matchWinner,
    result,
    resultMargin,
  } = match;

  const opponent = homeTeam === name ? awayTeam : homeTeam;
  const opponentTeamRoute = `/team/${opponent}`;

  return (
    <div className="MatchDetailsCard">
      <h3>Latest Matches</h3>
      <h4>Match Details</h4>
      <h3>
        vs&nbsp;
        <Link to={opponentTeamRoute}>{opponent}</Link>&nbsp; on {date}&nbsp;
        at&nbsp;
        {venue}, {city}
      </h3>
      <h3>
        {matchWinner} won by {resultMargin} {result}
      </h3>
    </div>
  );
};
