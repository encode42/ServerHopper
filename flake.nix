{
  description = "A Nix-flake-based Java development environment";

  inputs.nixpkgs.url = "github:NixOS/nixpkgs/release-26.05";

  outputs =
    { self, ... }@inputs:

    let
      javaVersion = 21; # Change this value to update the whole stack

      supportedSystems = [
        "x86_64-linux"
        "aarch64-linux"
        "aarch64-darwin"
      ];

      forEachSupportedSystem =
        f:
        inputs.nixpkgs.lib.genAttrs supportedSystems (
          system:
          f {
            inherit system;
            pkgs = import inputs.nixpkgs {
              inherit system;
              overlays = [ inputs.self.overlays.default ];
            };
          }
        );
    in
    {
      overlays.default =
        final: prev:
        let
          jdk = prev."jdk${toString javaVersion}";
        in
        {
          inherit jdk;
          gradle = prev.gradle_9.override { java = jdk; };
        };

      devShells = forEachSupportedSystem (
        { pkgs, system }:
        {
          default = pkgs.mkShellNoCC {
            packages = with pkgs; [
              gcc
              gradle
              jdk
              ncurses
              patchelf
              zlib

              rumdl
            ];
          };
        }
      );
    };
}
