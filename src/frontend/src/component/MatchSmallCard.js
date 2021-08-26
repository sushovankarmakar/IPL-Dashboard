import React from "react";
import { Link } from "react-router-dom";

import "./MatchSmallCard.scss";

export const MatchSmallCard = ({ teamName, match }) => {
  if (!match) return null;

  const { homeTeam, awayTeam, matchWinner, result, resultMargin } = match;

  const opponent = homeTeam === teamName ? awayTeam : homeTeam;
  const opponentTeamRoute = `/teams/${opponent}`;

  const isMatchWon = teamName === matchWinner;

  return (
    <div
      className={
        isMatchWon
          ? "match-small-card winner-card"
          : "match-small-card loser-card"
      }
    >
      <div>
        <span className="vs">vs</span>
        <h3>
          <Link to={opponentTeamRoute}>{opponent}</Link>
        </h3>
      </div>
      <div className="result">
        <h4>
          {matchWinner} won by {resultMargin} {result}
        </h4>
      </div>
    </div>
  );
};
