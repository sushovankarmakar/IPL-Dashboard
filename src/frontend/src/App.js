import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import "./App.css";
import { MatchPage } from "./page/MatchPage";
import { TeamPage } from "./page/TeamPage";

function App() {
  return (
    <div className="App">
      <Router>
        <Switch>
          <Route path="/team/:teamName/match/:year">
            <MatchPage />
          </Route>
          <Route path="/team/:teamName">
            <TeamPage />
          </Route>
        </Switch>
      </Router>
    </div>
  );
}

export default App;
