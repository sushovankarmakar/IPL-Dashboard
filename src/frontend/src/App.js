import { HashRouter as Router, Route, Switch } from "react-router-dom";
import "./App.scss";
import { MatchPage } from "./page/MatchPage";
import { TeamPage } from "./page/TeamPage";
import { HomePage } from "./page/HomePage";

function App() {
  return (
    <div className="App">
      <Router>
        <Switch>
          <Route path="/teams/:teamName/matches/:year">
            <MatchPage />
          </Route>
          <Route path="/teams/:teamName">
            <TeamPage />
          </Route>
          <Route path="/">
            <HomePage />
          </Route>
        </Switch>
      </Router>
    </div>
  );
}

export default App;
