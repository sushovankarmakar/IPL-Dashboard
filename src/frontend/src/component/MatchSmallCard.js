import React from "react";
import { Link } from "react-router-dom";

export const MatchSmallCard = ({ name, match }) => {
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
  const opponentTeamRoute = `/teams/${opponent}`;

  return (
    <div className="MatchSmallCard">
      <h4>
        vs&nbsp;
        <Link to={opponentTeamRoute}>{opponent}</Link>&nbsp; on {date}&nbsp;
        at&nbsp;{venue}, {city}
      </h4>
      <p>
        {matchWinner} won by {resultMargin} {result}
      </p>
    </div>
  );
};
