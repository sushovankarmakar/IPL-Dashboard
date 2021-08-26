import React from "react";
import { Link } from "react-router-dom";

import "./MatchDetailsCard.scss";

export const MatchDetailsCard = ({ teamName, match }) => {
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
    tossWinner,
    tossDecision,
    firstUmpire,
    secondUmpire,
    playerOfMatch,
  } = match;

  const opponent = homeTeam === teamName ? awayTeam : homeTeam;
  const opponentTeamRoute = `/teams/${opponent}`;
  const isMatchWon = teamName === matchWinner;

  return (
    <div
      className={
        isMatchWon
          ? "match-details-card winner-card"
          : "match-details-card loser-card"
      }
    >
      <div>
        <span className="vs">vs</span>
        <h1>
          <Link to={opponentTeamRoute}>{opponent}</Link>
        </h1>
        <p className="date">on {date}</p>
        <p className="venue">
          at&nbsp; {venue}, {city}
        </p>
        <h3 className="result">
          {matchWinner} won by {resultMargin} {result}
        </h3>
      </div>

      <div className="additional-data-section">
        <h4>Toss</h4>
        <p>
          {tossWinner} won the toss and
          <br />
          chose to {tossDecision} first
        </p>
        <h4>Umpires</h4>
        <p>
          {firstUmpire}, {secondUmpire}
        </p>
        <h4>Man of the match</h4>
        <p>{playerOfMatch}</p>
      </div>
    </div>
  );
};
